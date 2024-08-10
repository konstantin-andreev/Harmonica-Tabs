const audioContext = new (window.AudioContext || window.webkitAudioContext)();

function playNote(frequency, duration = 1, startTime = 0) {
    const oscillator = audioContext.createOscillator();
    oscillator.frequency.setValueAtTime(frequency, audioContext.currentTime + startTime);
    oscillator.type = 'triangle';

    const gainNode = audioContext.createGain();
    oscillator.connect(gainNode);
    gainNode.connect(audioContext.destination);

    const whiteNoise = audioContext.createBufferSource();
    const bufferSize = audioContext.sampleRate * duration;
    const buffer = audioContext.createBuffer(1, bufferSize, audioContext.sampleRate);
    const data = buffer.getChannelData(0);

    for (let i = 0; i < bufferSize; i++) {
        data[i] = (Math.random() * 2 - 1) * 0.05;
    }
    whiteNoise.buffer = buffer;
    whiteNoise.loop = true;
    whiteNoise.connect(gainNode);

    const vibrato = audioContext.createOscillator();
    vibrato.frequency.setValueAtTime(5, audioContext.currentTime + startTime);
    const vibratoGain = audioContext.createGain();
    vibratoGain.gain.setValueAtTime(3, audioContext.currentTime + startTime);
    vibrato.connect(vibratoGain).connect(oscillator.frequency);
    vibrato.start(audioContext.currentTime + startTime);
    vibrato.stop(audioContext.currentTime + startTime + duration);

    gainNode.gain.setValueAtTime(0.001, audioContext.currentTime + startTime);
    gainNode.gain.exponentialRampToValueAtTime(0.7, audioContext.currentTime + startTime + 0.1);
    gainNode.gain.setValueAtTime(0.7, audioContext.currentTime + startTime + duration - 0.1);
    gainNode.gain.exponentialRampToValueAtTime(0.001, audioContext.currentTime + startTime + duration);

    oscillator.start(audioContext.currentTime + startTime);
    oscillator.stop(audioContext.currentTime + startTime + duration + 0.05);

    whiteNoise.start(audioContext.currentTime + startTime);
    whiteNoise.stop(audioContext.currentTime + startTime + duration);
}

function playTab() {
    const tab = document.getElementById("tab").innerText;
    let notesAndDurations = tab.split(" ");

    let currentTime = 0;

    for (let i = 0; i < notesAndDurations.length; i++) {
        let [note, duration] = notesAndDurations[i].split("|");

        duration = parseFloat(duration);
        let frequency = getFrequency(note);

        if (frequency) {
            playNote(frequency, duration, currentTime);
            currentTime += duration;
        }
    }
}

function getFrequency(note) {
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

function messageUser(){
    const usernameSpan = document.getElementById("username");
    const textarea = document.getElementById("message-content");

    let receiverUsername = usernameSpan.innerText;
    let content = textarea.value;

    let obj = {receiverUsername, content}

    fetch('/message/user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(obj)
    })
        .then(() => textarea.value = "")
        .catch(error => console.error('Error:', error));
}