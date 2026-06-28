<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Chat Bot</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50 font-sans flex flex-col min-h-screen pb-20">

<!-- HEADER -->
<header class="bg-white p-4 shadow-sm border-b flex items-center">

    <a href="TrangChuController"
       class="border px-3 py-1.5 rounded hover:bg-gray-100">
        ⬅
    </a>

    <h1 class="flex-1 text-center font-bold">
        Chat Bot
    </h1>

</header>

<!-- CHAT BOX -->
<main id="chat-box"
      class="flex-1 max-w-3xl mx-auto w-full p-4 flex flex-col gap-4 overflow-y-auto">

    <!-- BOT DEFAULT MESSAGE -->
    <div class="flex items-start gap-2">

        <div class="w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center">
            🤖
        </div>

        <div class="bg-white border p-3 rounded-lg">
            <b class="text-blue-600">AI</b>
            <p>Xin chào! Tôi có thể giúp gì cho bạn?</p>
        </div>

    </div>

</main>

<!-- INPUT -->
<div class="fixed bottom-[60px] w-full bg-white border-t p-3">

    <div class="max-w-3xl mx-auto flex gap-2">

        <input id="chat-input"
               class="flex-1 border rounded-full px-4 py-2"
               placeholder="Nhập câu hỏi..."
               onkeydown="handleKey(event)">

        <button id="send-btn"
                onclick="sendMessage()"
                class="bg-blue-600 text-white px-5 rounded-full">

            Gửi

        </button>

    </div>

</div>

<script>

const chatBox = document.getElementById("chat-box");
const input = document.getElementById("chat-input");
const button = document.getElementById("send-btn");

function scrollBottom(){
    chatBox.scrollTop = chatBox.scrollHeight;
}

function escapeHtml(text){
    if(!text) return "";
    return text
        .replace(/&/g,"&amp;")
        .replace(/</g,"&lt;")
        .replace(/>/g,"&gt;");
}

/* USER MESSAGE */
function appendUser(message){

    chatBox.insertAdjacentHTML("beforeend",
        "<div class='flex justify-end'>" +
            "<div class='bg-blue-600 text-white p-3 rounded-lg max-w-[75%]'>" +
                "<b>Bạn</b><br>" +
                escapeHtml(message) +
            "</div>" +
        "</div>"
    );

    scrollBottom();
}

/* BOT MESSAGE */
function appendBot(message){

    chatBox.insertAdjacentHTML("beforeend",
        "<div class='flex gap-2'>" +
            "<div class='w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center'>🤖</div>" +
            "<div class='bg-white border p-3 rounded-lg max-w-[75%]'>" +
                "<b class='text-blue-600'>AI</b><br>" +
                escapeHtml(message) +
            "</div>" +
        "</div>"
    );

    scrollBottom();
}

/* SEND MESSAGE */
function sendMessage(){

    let message = input.value.trim();
    if(message === "") return;

    appendUser(message);
    input.value = "";

    button.disabled = true;

    fetch("ChatController", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: "message=" + encodeURIComponent(message)
    })
    .then(res => res.text())
    .then(data => appendBot(data))
    .catch(() => appendBot("Không thể kết nối chatbot"))
    .finally(() => {
        button.disabled = false;
        input.focus();
    });
}

/* ENTER KEY */
function handleKey(e){
    if(e.key === "Enter") sendMessage();
}

</script>

</body>
</html>