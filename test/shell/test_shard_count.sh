# shellcheck source=./test_runner.sh
dir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
# '.' means include in bach ;)
. "${dir}"/test_runner.sh
runner=$(get_test_runner "${1:-local}")

test_shard_count_support() {
  # set -e means "exit immediately on non-zero exit status"
  set -e
  bazel test test/scala_test:shard_test --incompatible_check_sharding_support
  set +e
}

$runner test_shard_count_support