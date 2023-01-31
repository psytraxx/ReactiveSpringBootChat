import "./App.css";
import { ChatList, ChatForm } from "./components";

function App() {
  return (
    <div className="App">
      <ChatList channelId={1} />
      <ChatForm channelId={1} sender="sender name" />
    </div>
  );
}

export default App;
