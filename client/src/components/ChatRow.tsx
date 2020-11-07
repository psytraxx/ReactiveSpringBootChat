import React from "react";
import { ChatMessage } from "../lib/types";

interface ChatRowProps {
  message: ChatMessage;
}

const ChatRow = ({ message }: ChatRowProps) => {
  return (
    <li>
      <small>
        {message.createdDate && new Date(message.createdDate).toLocaleString()}
      </small>
      <p>{message.message}</p>
    </li>
  );
};

export default ChatRow;
