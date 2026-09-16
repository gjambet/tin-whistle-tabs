---
id: debt-0008
title: Play testing and production release process is not defined
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

The current release workflow publishes GitHub prereleases containing debug APKs. There is no documented process for Play Console internal testing, closed testing, promotion between tracks, or production release approval.

## Affected components

- Release governance
- Google Play testing tracks
- Production deployment

## Impact

The project lacks a reproducible path from a successful CI build to a tested Play Store production release. Release readiness and promotion decisions would otherwise be performed manually without project-local traceability.

## Expected remediation outcome

Define a repeatable Play release lifecycle covering internal testing, required closed testing where applicable, release validation, and controlled promotion to production.

## Resolution criteria

- Internal test track procedure is documented and exercised.
- Closed-testing requirements applicable to the developer account are documented and satisfied before production access is requested.
- Release candidates can be mapped to a source commit/version.
- Production promotion includes an explicit validation checklist.
- Rollback or superseding-release handling is documented.

## History

- 2026-09-16: Identified during Play Store readiness review.
