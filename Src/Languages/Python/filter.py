import numpy as np
import matplotlib.pyplot as plt

# Constants
R = 3
C = 0.33  # F

# Frequency range in log scale
freq = np.logspace(0, 5, 1000)

# Calculate the transfer function
s = 1j * 2 * np.pi * freq
H = s / (s + 1)

# Amplitude response
amplitude = (2*np.pi*freq)/np.sqrt(1+pow((2*np.pi*freq),2))

# Plotting
plt.figure(figsize=(10, 6))
plt.semilogx(freq, amplitude, label='Amplitude Response')
plt.title('Amplitude Response of RC High-Pass Filter')
plt.xlabel('Frequency (Hz)')
plt.ylabel('Amplitude')
plt.grid(True)
plt.legend()
plt.show()
