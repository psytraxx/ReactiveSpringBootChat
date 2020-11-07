import React from "react";
import "./App.css";
import { ChatList, ChatForm } from "./components";

function App() {
  return (
    <div className="App">
      <ChatList channelId={1} />
      <ChatForm channelId={1} />
    </div>
  );
}

export default App;
