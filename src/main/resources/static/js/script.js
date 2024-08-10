const audioContext = new (window.AudioContext || window.webkitAudioContext)();

function playNote(frequency, duration = 1, startTime = 0) {
    const oscillator = audioContext.createOscillator();

    oscillator.frequency.setValueAtTime(frequency, audioContext.currentTime + startTime);

    oscillator.type = 'sawtooth';

    const gainNode = audioContext.createGain();

    oscillator.connect(gainNode);


    gainNode.connect(audioContext.destination);

    oscillator.start(audioContext.currentTime + startTime);

    gainNode.gain.setValueAtTime(0.001, audioContext.currentTime + startTime);
    gainNode.gain.exponentialRampToValueAtTime(0.7, audioContext.currentTime + startTime + 0.05);
    gainNode.gain.setValueAtTime(0.7, audioContext.currentTime + startTime + duration - 0.2);
    gainNode.gain.exponentialRampToValueAtTime(0.001, audioContext.currentTime + startTime + duration);

    const vibrato = audioContext.createOscillator();
    vibrato.frequency.setValueAtTime(6, audioContext.currentTime + startTime);
    const vibratoGain = audioContext.createGain();
    vibratoGain.gain.setValueAtTime(5, audioContext.currentTime + startTime);
    vibrato.connect(vibratoGain).connect(oscillator.frequency);
    vibrato.start(audioContext.currentTime + startTime);
    vibrato.stop(audioContext.currentTime + startTime + duration);

    oscillator.stop(audioContext.currentTime + startTime + duration + 0.05);
}

function playTab() {
    console.log("Play");
    const tab = document.getElementById("tab").innerText;
    let notes = tab.split(" ");
    console.log(notes);

    let noteDuration = 0.5;
    for (let i = 0; i < notes.length; i++) {
        let frequency = getFrequency(notes[i]);
        if (frequency) {
            playNote(frequency, noteDuration, i * noteDuration);
        }
    }
}

function getFrequency(note) {
    // Map the notes to their corresponding frequencies
    const frequencies = {
        "1": 262,
        "-1": 293,
        "2": 330,
        "-2": 392,
        "3": 392,
        "-3": 494,
        "4": 523,
        "-4": 587,
        "5": 659,
        "-5": 698,
        "6": 754,
        "-6": 880,
        "7": 1048,
        "-7": 969.6,
        "8": 1318,
        "-8": 1191,
        "9": 1568,
        "-9": 1397,
        "10": 2092,
        "-10": 1760
    };
    return frequencies[note];
}

