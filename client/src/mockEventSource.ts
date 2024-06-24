Object.defineProperty(window, 'EventSource', {
  writable: true,
  value: jest.fn().mockImplementation(() => ({
    close: jest.fn(() => {}),
    addEventListener: jest.fn((_event: string, _callback: (event: MessageEvent) => void) => {})
  }))
});
