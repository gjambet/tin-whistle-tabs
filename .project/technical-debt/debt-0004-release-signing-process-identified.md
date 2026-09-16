---
id: debt-0004
title: Release signing process is not defined or automated
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

The Android release build type has no project signing configuration and the GitHub Actions workflow contains no release keystore or upload-key handling.

## Affected components

- Android release signing
- GitHub Actions secrets
- Google Play App Signing

## Impact

A stable, reproducible signed release cannot currently be produced for Play Store upload. Ad-hoc signing would create key-loss and release-continuity risk.

## Expected remediation outcome

Define a Play App Signing-compatible release process using a dedicated upload key, with secret material stored outside the repository and injected securely in CI.

## Resolution criteria

- A dedicated upload key exists and is backed up securely.
- No private key or password is committed to Git.
- GitHub Actions can build a signed release AAB using repository/environment secrets.
- The generated AAB signature is verified before publication.
- The upload key is registered with the Play Console application.

## History

- 2026-09-16: Identified during Play Store readiness review.
