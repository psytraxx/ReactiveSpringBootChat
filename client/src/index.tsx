import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';

// biome-ignore lint/style/noNonNullAssertion: ignore
ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
