import './App.css';
import ChatForm from './components/ChatForm';
import ChatList from './components/ChatList';

function App() {
  return (
    <>
      <ChatList channelId={1} />
      <ChatForm channelId={1} sender="sender name" />
    </>
  );
}

export default App;
