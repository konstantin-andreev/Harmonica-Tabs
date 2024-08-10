package org.harmonicatabs.model.dtos;

public class SentMessageDTO {
    private String content;
    private String receiverUsername;

    public SentMessageDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
