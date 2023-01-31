import { Component } from "react";
import { ChatMessage } from "../lib/types";
import { ChatRow } from "./";

interface ChatListState {
  messages: ChatMessage[];
}

interface ChatListProps {
  channelId: number;
}

const url = process.env.API_BASE_URL
  ? process.env.API_BASE_URL
  : "http://localhost:8080";

class ChatList extends Component<ChatListProps, ChatListState> {
  state: ChatListState = { messages: [] };

  // After the component did mount, we set the state each second.
  componentDidMount() {
    const source = new EventSource(
      `${url}/chats/stream?channelId=${this.props.channelId}`
    );

    source.onmessage = (event) => {
      const messages = this.state.messages.concat(JSON.parse(event.data));
      this.setState({ messages });
    };
  }

  render() {
    const list = this.state.messages.map((row) => {
      return <ChatRow key={row.id} item={row} />;
    });

    return <ul className="chatlist">{list}</ul>;
  }
}
export default ChatList;
