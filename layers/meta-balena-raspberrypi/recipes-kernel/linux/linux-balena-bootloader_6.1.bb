LINUX_VERSION ?= "6.1.47"
LINUX_RPI_BRANCH ?= "pi5_61"
LINUX_RPI_KMETA_BRANCH ?= "yocto-6.1"

SRCREV_machine = "20e78d1110a8cd06f4170dc18bcaba10d7af8d2e"
SRCREV_meta = "f845a7f37d7114230d6609e2bd630070f2f6cd9b"

KMETA = "kernel-meta"

SRC_URI = " \
          git://git@github.com/balena-os/linux-2712.git;name=machine;branch=${LINUX_RPI_BRANCH};protocol=ssh \
          git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${LINUX_RPI_KMETA_BRANCH};destsuffix=${KMETA} \
"
SRC_URI:append = " file://rpi-kexec.cfg"
SRC_URI:remove = "file://initramfs-image-bundle.cfg"

require recipes-kernel/linux/linux-raspberrypi.inc

KERNEL_DTC_FLAGS += "-@ -H epapr"

INITRAMFS_IMAGE = "balena-image-bootloader-initramfs"

KERNEL_PACKAGE_NAME = "balena-bootloader"

KERNEL_DEVICETREE = "${RPI_KERNEL_DEVICETREE}"

PROVIDES = "virtual/balena-bootloader"
