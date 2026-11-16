# mergeguard-demo

Fixture repository used to verify [MergeGuard](../mergeguard) end-to-end.

Three pull requests intentionally contain different risk levels:

- `feature/refund-risk` - blocking security and data-loss defects.
- `feature/audit-log` - a fix-level reliability defect.
- `feature/order-note` - a clean change that should ship.

Do not deploy this code. The credentials are fake.
