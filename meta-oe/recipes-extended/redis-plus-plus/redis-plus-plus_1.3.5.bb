DESCRIPTION = "C++ client for Redis based on hiredis"
HOMEPAGE = "https://github.com/sewenew/redis-plus-plus"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/sewenew/redis-plus-plus;branch=master;protocol=https"
SRCREV = "58084931ed1a056d91fe96da7b9ea81fa023560a"

S = "${WORKDIR}/git"

inherit cmake

DEPENDS += "hiredis"

RDEPENDS:${PN} += "hiredis"

FILES_SOLIBSDEV = ""
FILES:${PN} += " ${libdir}/libredis++.so*"

INSANE_SKIP:${PN} += "dev-so"
