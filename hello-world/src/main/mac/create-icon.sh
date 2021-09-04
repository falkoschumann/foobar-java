#!/bin/bash

mkdir app-icon.iconset
sips -z 16 16 icon1024.png --out app-icon.iconset/icon_16x16.png
sips -z 32 32 icon1024.png --out app-icon.iconset/icon_16x16@2x.png
sips -z 32 32 icon1024.png --out app-icon.iconset/icon_32x32.png
sips -z 64 64 icon1024.png --out app-icon.iconset/icon_32x32@2x.png
sips -z 128 128 icon1024.png --out app-icon.iconset/icon_128x128.png
sips -z 256 256 icon1024.png --out app-icon.iconset/icon_128x128@2x.png
sips -z 256 256 icon1024.png --out app-icon.iconset/icon_256x256.png
sips -z 512 512 icon1024.png --out app-icon.iconset/icon_256x256@2x.png
sips -z 512 512 icon1024.png --out app-icon.iconset/icon_512x512.png
cp icon1024.png app-icon.iconset/icon_512x512@2x.png
iconutil -c icns app-icon.iconset
rm -R app-icon.iconset
