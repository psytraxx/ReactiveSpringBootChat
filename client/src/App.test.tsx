/// <reference types="@testing-library/jest-dom" />
import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import './mockEventSource.ts';

import App from './App';

describe('App component', () => {
  it('renders Send button from ChatForm', () => {
    render(<App />);
    // Assuming ChatForm renders a button with text "Send"
    const sendButton = screen.getByRole('button', { name: /Send/i });
    expect(sendButton).toBeInTheDocument();
  });

  it('renders ChatList component', () => {
    render(<App />);
    // Assuming ChatList renders a list element (e.g., <ul> or <ol>)
    const chatList = screen.getByRole('list');
    expect(chatList).toBeInTheDocument();
  });

  it('renders message input from ChatForm', () => {
    render(<App />);
    // Assuming ChatForm renders a text input field for the message
    const messageInput = screen.getByRole('textbox'); // Or a more specific selector like getByPlaceholderText if applicable
    expect(messageInput).toBeInTheDocument();
  });
});
