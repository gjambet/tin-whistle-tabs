---
id: debt-0003
title: CI does not produce a Play Store release bundle
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

`.github/workflows/android-build.yml` runs `assembleDebug` and publishes only a debug APK as the workflow artifact and GitHub prerelease asset.

## Affected components

- GitHub Actions
- Android release packaging
- Google Play delivery

## Impact

The current pipeline is useful for direct installation testing but does not produce the Android App Bundle required for Play Store distribution.

## Expected remediation outcome

Extend CI to build a release AAB while retaining the existing debug APK workflow for direct testing.

## Resolution criteria

- CI builds a release bundle using the release build type.
- A versioned `.aab` artifact is retained by GitHub Actions.
- The existing debug APK remains available for direct installation/testing.
- The generated bundle passes Gradle verification and can be accepted by a Play Console test track.

## History

- 2026-09-16: Identified during Play Store readiness review.
