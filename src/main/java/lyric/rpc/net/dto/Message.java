package lyric.rpc.net.dto;

import java.io.Serializable;

public class Message implements Serializable {
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public Message() {
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
