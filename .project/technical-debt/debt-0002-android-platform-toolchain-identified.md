---
id: debt-0002
title: Android platform and build toolchain are outdated for Play distribution
status: identified
created: 2026-09-16
updated: 2026-09-16
relationships:
  related: []
  affects: []
  remediated-by: []
  verified-by: []
---

## Evidence

`Irish_Whistle/app/build.gradle` currently uses `compileSdkVersion 33` and `targetSdkVersion 33`. The root Android build uses Android Gradle Plugin 7.4.1 and the wrapper uses Gradle 7.5.

## Affected components

- Android compilation
- Play Store compatibility
- Dependency maintenance
- CI build environment

## Impact

The current platform target is below the level required for new Play Store releases, and the existing Android Gradle Plugin/Gradle combination is too old for a clean API 36 migration. This increases migration cost and blocks store readiness.

## Expected remediation outcome

Move the application to Android API 36 with a compatible, supported Android Gradle Plugin and Gradle wrapper while retaining Java 17 compatibility in CI.

## Resolution criteria

- `compileSdk` is 36 or later.
- `targetSdk` is 36 or later.
- Android Gradle Plugin and Gradle wrapper versions are mutually compatible and support the selected SDK.
- Debug and release builds succeed in GitHub Actions.
- Existing application functionality passes smoke testing on a current Android device/emulator.

## History

- 2026-09-16: Identified during Play Store readiness review.
