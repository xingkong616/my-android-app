#!/usr/bin/env bash
set -euo pipefail

echo "==> Installing Gradle 8.10..."
GRADLE_VERSION=8.10.2
if ! command -v gradle >/dev/null 2>&1; then
  curl -fsSL "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" -o /tmp/gradle.zip
  sudo unzip -q /tmp/gradle.zip -d /opt/
  sudo ln -sf "/opt/gradle-${GRADLE_VERSION}/bin/gradle" /usr/local/bin/gradle
  rm /tmp/gradle.zip
fi

echo "==> Generating Gradle wrapper..."
cd "$(dirname "$0")/.."
if [ ! -f gradlew ]; then
  gradle wrapper --gradle-version="${GRADLE_VERSION}" --distribution-type=bin
  chmod +x gradlew
fi

echo "==> Accepting Android SDK licenses..."
yes | sdkmanager --licenses >/dev/null 2>&1 || true

echo "==> Installing Android SDK components..."
sdkmanager \
  "platform-tools" \
  "platforms;android-35" \
  "build-tools;35.0.0" >/dev/null

echo "==> Pre-downloading Gradle dependencies (may take a few minutes)..."
./gradlew --no-daemon help >/dev/null 2>&1 || true

echo "==> Setup complete! Try: ./gradlew assembleDebug"
