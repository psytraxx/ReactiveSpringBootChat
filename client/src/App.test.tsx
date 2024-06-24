import { render, screen } from '@testing-library/react';
import './mockEventSource.ts';

import App from './App';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/Send/i);
  expect(linkElement).toBeDefined();
});
