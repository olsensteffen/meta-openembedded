DESCRIPTION = "dlm control daemon and tool"

SECTION = "utils"
HOMEPAGE = "https://fedorahosted.org/cluster/wiki/HomePage"

REQUIRED_DISTRO_FEATURES = "systemd"

SRC_URI = "https://pagure.io/dlm/archive/dlm-${PV}/dlm-dlm-${PV}.tar.gz \
           file://0001-dlm-fix-compile-error-since-xml2-config-should-not-b.patch \
           file://0001-Include-sys-sysmacros.h-for-major-minor-macros-in-gl.patch \
           file://0001-make-Replace-cp-a-with-mode-preserving-options.patch \
           file://0004-include-string.h-for-memset-prototype.patch \
           "

SRC_URI[sha256sum] = "f12c0056b9196dfcecbec2fa8930feb87c605a86ef0f3d7bd6fb0b77cd7f45ca"

UPSTREAM_CHECK_URI = "https://pagure.io/dlm/releases"
UPSTREAM_CHECK_REGEX = "dlm-(?P<pver>\d+(\.\d+)+)"

LICENSE = "LGPL-2.0-or-later & GPL-2.0-only & GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://README.license;md5=8f0bbcdd678df1bce9863492b6c8832d"

S = "${WORKDIR}/dlm-dlm-${PV}"

DEPENDS += "corosync"

inherit pkgconfig systemd features_check

PACKAGECONFIG ??= ""

PACKAGECONFIG[pacemaker] = ",,pacemaker"

SYSTEMD_SERVICE:${PN} = "dlm.service"
SYSTEMD_AUTO_ENABLE = "enable"

export EXTRA_OEMAKE = ""

DONTBUILD = "${@bb.utils.contains('PACKAGECONFIG', 'pacemaker', '', 'fence', d)}"

do_compile:prepend:toolchain-clang() {
    sed -i -e "s/-fstack-clash-protection//g" ${S}/*/Makefile
}

do_compile() {
    sed -i "s/libsystemd-daemon/libsystemd/g" ${S}/dlm_controld/Makefile
    sed -i -e "s/ ${DONTBUILD}//g" ${S}/Makefile
    oe_runmake 'CC=${CC}'
}

do_install() {
    oe_runmake install DESTDIR=${D} LIBDIR=${libdir}
    install -Dm 0644 ${S}/init/dlm.sysconfig ${D}${sysconfdir}/sysconfig/dlm
    install -Dm 0644 ${S}/init/dlm.init ${D}${sysconfdir}/init.d/dlm

    # install systemd unit files
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -Dm 0644 ${S}/init/dlm.service ${D}${systemd_unitdir}/system/dlm.service
    fi
}

