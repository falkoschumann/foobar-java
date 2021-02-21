#!/bin/bash

mkdir AppIcon.iconset
sips -z 16 16     ../templates/Icon1024.png --out AppIcon.iconset/icon_16x16.png
sips -z 32 32     ../templates/Icon1024.png --out AppIcon.iconset/icon_16x16@2x.png
sips -z 32 32     ../templates/Icon1024.png --out AppIcon.iconset/icon_32x32.png
sips -z 64 64     ../templates/Icon1024.png --out AppIcon.iconset/icon_32x32@2x.png
sips -z 128 128   ../templates/Icon1024.png --out AppIcon.iconset/icon_128x128.png
sips -z 256 256   ../templates/Icon1024.png --out AppIcon.iconset/icon_128x128@2x.png
sips -z 256 256   ../templates/Icon1024.png --out AppIcon.iconset/icon_256x256.png
sips -z 512 512   ../templates/Icon1024.png --out AppIcon.iconset/icon_256x256@2x.png
sips -z 512 512   ../templates/Icon1024.png --out AppIcon.iconset/icon_512x512.png
cp ../templates/Icon1024.png AppIcon.iconset/icon_512x512@2x.png
iconutil -c icns AppIcon.iconset
rm -R AppIcon.iconset
