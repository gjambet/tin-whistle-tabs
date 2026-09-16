---
id: debt-0007
title: GPL distribution compliance for the fork is not formalized
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

The repository contains the upstream GPLv2 license and the application is a fork, but the Play Store distribution process does not yet document how source availability, original attribution, fork identification, and license visibility will be presented to end users.

## Affected components

- About screen
- Store listing
- Source distribution
- Release documentation

## Impact

Publishing binaries without a deliberate compliance process risks incomplete attribution or source-access information and makes future release review dependent on manual knowledge.

## Expected remediation outcome

Make the fork status, upstream attribution, GPLv2 licensing, and corresponding source-code availability explicit in both the application and its distribution documentation.

## Resolution criteria

- Original author attribution remains visible.
- The application clearly identifies itself as a fork where appropriate.
- GPLv2 license information remains available.
- Store/release documentation provides a stable link to the corresponding source code.
- The source repository contains the source and build scripts corresponding to each distributed release.

## History

- 2026-09-16: Identified during Play Store readiness review.
