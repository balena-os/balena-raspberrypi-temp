deviceTypesCommon = require '@resin.io/device-types/common'
{ networkOptions, commonImg, instructions } = deviceTypesCommon

module.exports =
	version: 1
	slug: 'raspberrypi5-64'
	aliases: [ 'raspberrypi5-64' ]
	name: 'Raspberry Pi 5'
	arch: 'aarch64'
	state: 'released'

	instructions: commonImg.instructions
	gettingStartedLink:
		windows: 'https://www.balena.io/docs/learn/getting-started/raspberrypi5/nodejs/'
		osx: 'https://www.balena.io/docs/learn/getting-started/raspberrypi5/nodejs/'
		linux: 'https://www.balena.io/docs/learn/getting-started/raspberrypi5/nodejs/'

	options: [ networkOptions.group ]

	yocto:
		machine: 'raspberrypi5-64'
		image: 'balena-image'
		fstype: 'balenaos-img'
		version: 'yocto-kirkstone'
		deployArtifact: 'balena-image-raspberrypi5-64.balenaos-img'
		compressed: true

	configuration:
		config:
			partition:
				primary: 1
			path: '/config.json'

	initialization: commonImg.initialization
