SUMMARY = "Header-only C++ library for JSON Schema validation"
HOMEPAGE = "https://github.com/tristanpenman/valijson"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=015106c62262b2383f6c72063f0998f2"

SRC_URI = "git://github.com/tristanpenman/valijson.git;branch=master;protocol=https"
SRCREV = "94d3bfd39ad4dca1be0f700b5eea8e4234d0e7e8"

S = "${WORKDIR}/git"

inherit cmake

PACKAGECONFIG ?= "boost"

PACKAGECONFIG[boost] = "-Dvalijson_EXCLUDE_BOOST=FALSE,-Dvalijson_EXCLUDE_BOOST=TRUE,boost"
PACKAGECONFIG[examples] = "-Dvalijson_BUILD_EXAMPLES=TRUE,-Dvalijson_BUILD_EXAMPLES=FALSE,curlpp"
PACKAGECONFIG[tests] = "-Dvalijson_BUILD_TESTS=TRUE,-Dvalijson_BUILD_TESTS=FALSE,curlpp"

# valijson is a header only C++ library, so the main package will be empty.
RDEPENDS:${PN}-dev = ""

BBCLASSEXTEND = "native nativesdk"

do_install() {
    install -d ${D}${includedir}/compat
    install -d ${D}${includedir}/valijson
    install -d ${D}${includedir}/valijson/adapters
    install -d ${D}${includedir}/valijson/constraints
    install -d ${D}${includedir}/valijson/internal
    install -d ${D}${includedir}/valijson/utils

    install -m 0644 ${S}/include/compat/* ${D}${includedir}/compat
    install -D -m 0644 ${S}/include/valijson/*.hpp -t ${D}${includedir}/valijson
    install -D -m 0644 ${S}/include/valijson/adapters/*.hpp -t ${D}${includedir}/valijson/adapters
    install -D -m 0644 ${S}/include/valijson/constraints/*.hpp -t ${D}${includedir}/valijson/constraints
    install -D -m 0644 ${S}/include/valijson/internal/*.hpp -t ${D}${includedir}/valijson/internal
    install -D -m 0644 ${S}/include/valijson/utils/*.hpp -t ${D}${includedir}/valijson/utils
}
