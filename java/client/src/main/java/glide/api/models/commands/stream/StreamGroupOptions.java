/** Copyright Valkey GLIDE Project Contributors - SPDX Identifier: Apache-2.0 */
package glide.api.models.commands.stream;

import glide.api.commands.StreamBaseCommands;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

/**
 * Optional arguments for {@link StreamBaseCommands#xgroupCreate(String, String, String,
 * StreamGroupOptions)}
 *
 * @see <a href="https://valkey.io/commands/xgroup-create/">valkey.io</a>
 */
@Builder
public final class StreamGroupOptions {

    // Valkey API String argument for makeStream
    public static final String MAKE_STREAM_VALKEY_API = "MKSTREAM";

    // Valkey API String argument for entriesRead
    public static final String ENTRIES_READ_VALKEY_API = "ENTRIESREAD";

    /**
     * If <code>true</code> and the stream doesn't exist, creates a new stream with a length of <code>
     * 0</code>.
     */
    @Builder.Default private boolean mkStream = false;

    public static class StreamGroupOptionsBuilder {

        /** If the stream doesn't exist, this creates a new stream with a length of <code>0</code>. */
        public StreamGroupOptionsBuilder makeStream() {
            return mkStream(true);
        }
    }

    /**
     * A value representing the number of stream entries already read by the group.
     *
     * @since Redis 7.0.0
     */
    private Long entriesRead;

    /**
     * Converts options and the key-to-id input for {@link StreamBaseCommands#xgroupCreate(String,
     * String, String, StreamGroupOptions)} into a String[].
     *
     * @return String[]
     */
    public String[] toArgs() {
        List<String> optionArgs = new ArrayList<>();

        if (this.mkStream) {
            optionArgs.add(MAKE_STREAM_VALKEY_API);
        }

        if (this.entriesRead != null) {
            optionArgs.add(ENTRIES_READ_VALKEY_API);
            optionArgs.add(entriesRead.toString());
        }

        return optionArgs.toArray(new String[0]);
    }
}
