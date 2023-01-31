import { ChatMessage } from "../lib/types";

interface ChatRowProps {
  item: ChatMessage;
}

const ChatRow = ({ item }: ChatRowProps) => {
  return (
    <li>
      <small>{item.sender}</small>
      <p>{item.message}</p>
      <small>
        {item.createdDate && new Date(item.createdDate).toLocaleString()}
      </small>
    </li>
  );
};

export default ChatRow;
