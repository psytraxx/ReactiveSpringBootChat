class MockEventSource {
  onmessage: ((ev: MessageEvent) => void) | null = null;
  constructor(_url?: string) { }
  addEventListener(_event: string, _callback: (event: MessageEvent) => void) {
    if (_event === 'message') this.onmessage = _callback;
  }
  close() { }
}

Object.defineProperty(window, 'EventSource', {
  writable: true,
  value: MockEventSource
});
