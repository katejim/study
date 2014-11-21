#ifndef ERRORS_H
#define ERRORS_H

#include <stdexcept>
#include <string>

using std::string;


class Exception : public std::exception
{
    string msg;
    //size_t erLine;
public:
    Exception(string const &message) : msg(message) {}

    const char* what() const throw()
    {
        return msg.c_str();
    }
    //    size_t getLine() const
    //    {
    //        return erLine;
    //    }
    ~Exception() throw () {}
};
#endif // ERRORS_H
