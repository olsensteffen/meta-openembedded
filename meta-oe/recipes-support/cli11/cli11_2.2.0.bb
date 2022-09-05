SUMMARY = "C++11 command line parser"
DESCRIPTION = "A command line parser for C++11 and beyond that provides a rich feature set with a simple and intuitive interface."
HOMEPAGE = "https://github.com/CLIUtils/CLI11"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9ad746b5f49c0fd53c08ca1faff1922c"
SRCREV = "b9be5b9444772324459989177108a6a65b8b2769"
PV .= "+git${SRCPV}"

SRC_URI += "gitsm://github.com/CLIUtils/CLI11;branch=main;protocol=https"

S = "${WORKDIR}/git"

inherit cmake
inherit ptest

# cli11 is a header only C++ library, so the main package will be empty.
RDEPENDS:${PN}-dev = ""
