/Users/z19223188/Library/Android/sdk/emulator/emulator -avd Nexus6v11 -wipe-data &
EMULATOR_PID=$

WAIT_CMD="/Users/z19223188/Library/Android/sdk/platform-tools/adb wait-for-device shell getprop init.svc.bootanim"
until $WAIT_CMD | grep -m 1 stopped; do
    echo "Waiting..."
    sleep 1
done
