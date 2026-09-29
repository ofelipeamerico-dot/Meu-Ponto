import wave, struct, math, sys
r = 44100
out = []
def tone(f, d):
    n = int(r * d)
    for i in range(n):
        env = min(1, i / 300, (n - i) / 300)
        out.append(int(30000 * env * math.sin(2 * math.pi * f * i / r)))
def pause(d):
    out.extend([0] * int(r * d))
for _ in range(4):
    for _ in range(3):
        tone(1000, 0.15); pause(0.08)
    pause(0.4)
w = wave.open(sys.argv[1], "wb")
w.setnchannels(1); w.setsampwidth(2); w.setframerate(r)
w.writeframes(b"".join(struct.pack("<h", s) for s in out))
w.close()
