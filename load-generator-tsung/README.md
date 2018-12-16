# Load generator tsung

A load generator container based on tsung (http://tsung.erlang-projects.org/user_manual/index.html).

The docker container runs tsung and exercises a single entrypoint named `entry-1` on port `8080`. This entrypoint is expected to be an instance of the `mad-service` container.
