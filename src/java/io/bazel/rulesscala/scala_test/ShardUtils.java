package io.bazel.rulesscala.scala_test;

import java.io.File;
import java.io.IOException;

public class ShardUtils {
    /**
     * ENV var set by bazel to pass the number of test shards
     */
    private static final String TEST_TOTAL_SHARDS = "TEST_TOTAL_SHARDS";

    /**
     * File that Bazel touches to indicate that it is sharding tests
     */
    private static final String TEST_SHARD_STATUS_FILE = "TEST_SHARD_STATUS_FILE";

    /**
     * ENV var set by bazel as the current index for shard count
     */
    private static final String TEST_SHARD_INDEX = "TEST_SHARD_INDEX";

    /**
     * Return true iff the current test should be sharded.
     */
    public static boolean isShardingEnabled() {
        return System.getenv("TEST_TOTAL_SHARDS") != null;
    }


    /**
     * Returns the current shard index, with a default of 0
     */
    public static int getShardIndex() {
        String shardIndex = System.getenv(TEST_SHARD_INDEX);
        return shardIndex == null ? 0 : Integer.parseInt(shardIndex);
    }

    /**
     * Returns the current value for shard_count, with a default of 1
     */
    public static int getTotalShards() {
        String totalShards = System.getenv(TEST_TOTAL_SHARDS);
        return totalShards == null ? 1 : Integer.parseInt(totalShards);
    }

    /**
     * Creates the shard file that is used to indicate that tests are
     * being sharded.
     */
    public static void touchShardFile() {
        String shardStatusPath = System.getenv(TEST_SHARD_STATUS_FILE);
        File shardFile = (shardStatusPath == null ? null : new File(shardStatusPath));
        touchShardFile(shardFile);
    }

    static void touchShardFile(File shardFile) {
        if (shardFile != null) {
            try {
                if (!shardFile.createNewFile() && !shardFile.setLastModified(System.currentTimeMillis())) {
                    throw new IOException("Unable to update modification time of " + shardFile);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error writing shard file " + shardFile, e);
            }
        }
    }
}
