---
id: debt-0001
title: Package identity collides with upstream application
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

`Irish_Whistle/app/build.gradle` currently uses `applicationId "fr.charleslabs.tinwhistletabs"`, which is the package identity of the upstream application.

## Affected components

- Android application identity
- Google Play distribution
- Upgrade/install semantics

## Impact

The fork cannot be published as an independent Play Store application while retaining the upstream package identity. It also creates ambiguity between the fork and the original application.

## Expected remediation outcome

Assign the fork a unique application ID controlled by this project. Keep or migrate the Java namespace deliberately, with the final choice documented.

## Resolution criteria

- The release build uses a unique `applicationId` owned by this project.
- The application installs independently from the upstream application.
- The chosen namespace/package migration strategy is documented and verified by a successful build.

## History

- 2026-09-16: Identified during Play Store readiness review.
