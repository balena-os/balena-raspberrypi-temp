LINUX_VERSION ?= "6.1.54"
LINUX_RPI_BRANCH ?= "pi5_61"
LINUX_RPI_KMETA_BRANCH ?= "yocto-6.1"

SRCREV_machine = "d6e6b9afa8fa93b97c61f1617e1b1590583bc7a7"
SRCREV_meta = "f845a7f37d7114230d6609e2bd630070f2f6cd9b"

KMETA = "kernel-meta"

SRC_URI = " \
          git://git@github.com/balena-os/linux-2712.git;name=machine;branch=${LINUX_RPI_BRANCH};protocol=ssh \
          git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${LINUX_RPI_KMETA_BRANCH};destsuffix=${KMETA} \
"
SRC_URI:append = " file://rpi-kexec.cfg"
SRC_URI:remove = "file://initramfs-image-bundle.cfg"

require recipes-kernel/linux/linux-raspberrypi.inc

do_deploy:append () {
    BOOTENV_FILE="${DEPLOYDIR}/${KERNEL_PACKAGE_NAME}/bootenv"
    grub-editenv "${BOOTENV_FILE}" create
    grub-editenv "${BOOTENV_FILE}" set "resin_root_part=A"
    grub-editenv "${BOOTENV_FILE}" set "bootcount=0"
    grub-editenv "${BOOTENV_FILE}" set "upgrade_available=0"
}

do_deploy[depends] += " grub-native:do_populate_sysroot"

KERNEL_DTC_FLAGS += "-@ -H epapr"

INITRAMFS_IMAGE = "balena-image-bootloader-initramfs"

KERNEL_PACKAGE_NAME = "balena-bootloader"

KERNEL_DEVICETREE = "${RPI_KERNEL_DEVICETREE}"

PROVIDES = "virtual/balena-bootloader"
