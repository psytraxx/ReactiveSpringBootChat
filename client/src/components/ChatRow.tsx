import React from "react";
import { ChatMessage } from "../lib/types";

interface ChatRowProps {
  message: ChatMessage;
}

const ChatRow = ({ message }: ChatRowProps) => {
  return (
    <li>
      {message.createdDate && new Date(message.createdDate).toLocaleString()}
      {message.message}
    </li>
  );
};

export default ChatRow;
