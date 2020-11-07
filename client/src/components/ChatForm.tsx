import React, { ChangeEvent, KeyboardEvent, MouseEvent } from "react";
import { addMessage } from "../lib/api";

interface ChatFormProps {
  channelId: number;
}

const ChatForm = ({ channelId }: ChatFormProps) => {
  const [message, setMessage] = React.useState("");
  const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
    setMessage(event.target.value);
  };

  const sendMessage = () => {
    if (message !== "") {
      addMessage({ channelId, message });
      setMessage("");
    }
  };

  const handleKeyDown = (e: KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") {
      sendMessage();
    }
  };

  const handleSubmit = (e: MouseEvent) => {
    e.preventDefault();
    sendMessage();
  };

  return (
    <form>
      <input
        id="message"
        type="text"
        placeholder="Please Enter"
        value={message}
        onKeyDown={handleKeyDown}
        onChange={handleChange}
      />

      <button onClick={handleSubmit} type="submit">
        Send
      </button>
    </form>
  );
};

export default ChatForm;
