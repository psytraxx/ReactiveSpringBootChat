import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { ChatList, ChatForm } from "./components";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      </header>
      <ChatList channelId={1} />
      <ChatForm channelId={1} />
    </div>
  );
}

export default App;
