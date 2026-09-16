---
id: debt-0005
title: Play Store application listing is not established
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

The project currently distributes debug APKs through GitHub releases and has no documented Play Console application entry, store listing, or package registration for this fork.

## Affected components

- Release management
- Google Play distribution
- Application identity

## Impact

There is no managed Play Store destination for release bundles, and distribution metadata is not versioned or documented alongside the application lifecycle.

## Expected remediation outcome

Create and document the Play Console application for Tin Whistle Tabs using the fork's unique package identity and establish ownership of the store listing.

## Resolution criteria

- A Play Console application exists for Tin Whistle Tabs.
- Its package identity matches the fork's release `applicationId`.
- Application name, category, contact information, and distribution settings are configured.
- The Play Console application identifier and release procedure are documented without storing account secrets in the repository.

## History

- 2026-09-16: Identified during Play Store readiness review.
