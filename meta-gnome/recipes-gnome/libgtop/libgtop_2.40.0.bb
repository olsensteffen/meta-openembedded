SUMMARY = "A library for collecting system monitoring data"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit gnomebase lib_package gtk-doc gobject-introspection gettext upstream-version-is-even features_check

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

SRC_URI += "file://0001-fix-compile-error-for-cross-compile.patch \
            file://0001-Pass-correct-parameter.patch \
            "

SRC_URI[archive.sha256sum] = "78f3274c0c79c434c03655c1b35edf7b95ec0421430897fb1345a98a265ed2d4"

DEPENDS = "glib-2.0 libxau"
