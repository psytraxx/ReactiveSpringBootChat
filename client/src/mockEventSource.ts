import { vi } from 'vitest';

Object.defineProperty(window, 'EventSource', {
  writable: true,
  value: vi.fn().mockImplementation(() => ({
    close: vi.fn(() => {}),
    addEventListener: vi.fn((_event: string, _callback: (event: MessageEvent) => void) => {})
  }))
});
