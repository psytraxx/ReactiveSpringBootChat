import { type FC, useEffect, useState } from 'react';
import { getBaseUrl } from '../lib/api';
import type { ChatMessage } from '../lib/types';
import ChatRow from './ChatRow';

interface ChatListProps {
  channelId: number;
}

const ChatList: FC<ChatListProps> = ({ channelId }) => {
  const [messages, setMessages] = useState<ChatMessage[]>([]);

  useEffect(() => {
    const source = new EventSource(`${getBaseUrl()}/chats/stream?channelId=${channelId}`);

    source.onmessage = (event) => {
      const newMessage = JSON.parse(event.data) as ChatMessage;
      setMessages((prevMessages) => [...prevMessages, newMessage]);
    };

    return () => {
      source.close();
    };
  }, [channelId]);

  const list = messages.map((row) => <ChatRow key={row.id} item={row} />);

  return <ul className="chatlist">{list}</ul>;
};

export default ChatList;
