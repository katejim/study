
#include <netinet/in.h>
#include <netinet/in_systm.h>
#include <netinet/ip.h>
#include <netinet/ip_icmp.h>

#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <iostream>

#include <sys/types.h>
#include <unistd.h>
#include <sys/time.h>


using std::cout;
using std::endl;
struct timeval tvorig, tvrecv;
long   tsorig, tsrecv;

int MAX_SIZE =  4096;
int SEC_PER_DAY = 86400;

void show_nice_time(time_t time, std::string value){
    struct tm *recieved_tm = localtime(&time);
    std::cout << value <<  recieved_tm->tm_hour << ":" << recieved_tm->tm_min << ":" << recieved_tm->tm_sec << std::endl;
}

u_short check_sum(u_short* data, int len)
{
    register int nleft = len;
    register int sum = 0;
    register u_short* w = data;
    u_short answer = 0;
    while (nleft > 1) {
        sum += *w++;
        nleft -= 2;
    }
    if (nleft == 1) {
        *(u_char*)(&answer) = *(u_char*)w;
        sum += answer;
    }
    sum = (sum >> 16) + (sum & 0xffff);
    sum += (sum >> 16);
    answer = ~sum;
    return (answer);
}

icmp prepare_packet(){
    icmp icmp_request;
    icmp_request.icmp_type = ICMP_TSTAMP;
    icmp_request.icmp_code = 0;
    icmp_request.icmp_cksum = 0;
    icmp_request.icmp_seq = 12345;
    icmp_request.icmp_id = getpid();

    gettimeofday(&tvorig, (struct timezone *)NULL);
    tsorig = (tvorig.tv_sec % SEC_PER_DAY) * 1000 + tvorig.tv_usec / 1000;

    icmp_request.icmp_otime = htonl(tsorig);
    icmp_request.icmp_rtime = 0;
    icmp_request.icmp_ttime = 0;
    icmp_request.icmp_cksum = check_sum((uint16_t *) &icmp_request, sizeof(icmp_request));

    return icmp_request;
}

void send_packet(int socket, icmp packet, sockaddr_in destination){
    int send = sendto(socket, &packet, sizeof(packet),0,(sockaddr const *) &destination,sizeof(destination));
    if (send == -1 || send != sizeof(packet)){
        perror("something wrong while sending");
        exit(errno);
    }
}

icmp * get_packet(int socket){
    icmp *icmp_response = NULL;
    ip * ip_response = NULL;
    do {
        char response[MAX_SIZE];
        ssize_t bytesReceived = recv(socket, response, MAX_SIZE, 0);
        if (bytesReceived < 0) {
//            std::cerr << "something wrong " << errno << endl;
            if (errno == 35)
                  std::cerr << "timeout error" <<std::endl;
            exit(errno);
        }
        ip_response = (ip *) response;
        icmp_response = (icmp *) (response + (ip_response->ip_hl << 2));
    } while (icmp_response->icmp_type != 14);
    return icmp_response;
}

int main(int argc, char *argv[])
{
    if (argc < 2){
        cout << "You should specify host adress" <<endl;
    }

    int new_socket = socket(AF_INET, SOCK_RAW, IPPROTO_ICMP);
    if(new_socket < 0)
    {
        perror("socket");
        exit(errno);
    }

    struct timeval t_recv = {0};
    t_recv.tv_sec = 2;
    setsockopt(new_socket, SOL_SOCKET, SO_RCVTIMEO, &t_recv, sizeof(timeval));

    struct sockaddr_in sock_addr;
    sock_addr.sin_family = AF_INET;
    int inet_pton_res = inet_pton(AF_INET, argv[1], &(sock_addr.sin_addr));
    if (inet_pton_res < 1) {
        if (inet_pton_res == 0)
            perror("src does not contain a character string\
                   representing a valid network address in the specified address family");
                   else
                   perror("not valid adress family");

    }

    icmp icmp_request = prepare_packet();
    send_packet(new_socket, icmp_request, sock_addr);
    icmp * return_packet = get_packet(new_socket);

    close(new_socket);



    gettimeofday(&tvorig, (struct timezone *)NULL);
    long current_time = (tvorig.tv_sec % SEC_PER_DAY) * 1000 + tvorig.tv_usec / 1000;


    long travel_time = ((ntohl(return_packet->icmp_rtime) - ntohl(return_packet->icmp_otime)) +  (current_time - ntohl(return_packet->icmp_ttime))) / 2;
    long delta_time = (ntohl(return_packet->icmp_rtime) - ntohl(return_packet->icmp_otime)) - travel_time;

    show_nice_time(ntohl(return_packet->icmp_rtime) / 1000, "server time: ");
    show_nice_time((time_t)tvorig.tv_sec, "current time: ");
    cout << "travel_time = " << travel_time <<" " <<"diff: " << delta_time <<   endl;
    return 0;
}

