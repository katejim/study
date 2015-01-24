cmake_minimum_required(VERSION 2.8.4)
project(icmp)

find_package(Boost COMPONENTS system filesystem REQUIRED)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp)
add_executable(icmp ${SOURCE_FILES})

target_link_libraries(icmp
  pthread
  ${Boost_SYSTEM_LIBRARY}
)
