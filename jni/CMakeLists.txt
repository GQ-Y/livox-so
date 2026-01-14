cmake_minimum_required(VERSION 3.0)
project(LivoxJNI)

# Find Java
find_package(Java REQUIRED)
find_package(JNI REQUIRED)

# Include directories
include_directories(
    ${JNI_INCLUDE_DIRS}
    ${CMAKE_CURRENT_SOURCE_DIR}/../livox-sdk2-official/include
    ${CMAKE_CURRENT_SOURCE_DIR}/../livox-sdk2-official/sdk_core
)

# Link directories
link_directories(/usr/local/lib)
link_directories(${CMAKE_CURRENT_SOURCE_DIR}/../livox-sdk2-official/build/sdk_core)

# Source files
set(SOURCES
    LivoxJNI.cpp
)

# Create shared library
add_library(livoxjni SHARED ${SOURCES})

# Link libraries
# Use full path to SDK library
set(SDK_LIB_DIR "${CMAKE_CURRENT_SOURCE_DIR}/../livox-sdk2-official/build/sdk_core")
target_link_directories(livoxjni PRIVATE ${SDK_LIB_DIR})
target_link_libraries(livoxjni
    ${JNI_LIBRARIES}
    livox_lidar_sdk_shared
    pthread
)

# Set output directory and library name based on platform
if(APPLE)
    set_target_properties(livoxjni PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../lib
        SUFFIX ".dylib"
    )
else()
    set_target_properties(livoxjni PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../lib
        SUFFIX ".so"
    )
endif()
