const { Server } = require("socket.io");

const io = new Server({ cors: "localhost:3000" });

let users = [];

io.on("connection", (socket) => {
  console.log("new connection: " + socket.id);
  socket.on("addNewUser", (userId) => {
    !users.some((user) => user.userId === userId) &&
      users.push({
        userId,
        socketId: socket.id,
      });
    console.log(users.length);
    io.emit("getOnlineUsers", users);
  });

  socket.on("sendMessage", (message) => {
    console.log(message);
    const user = users.find((user) => user.userId === message.to);
    console.log(user);
    if (user) {
      console.log(user.socketId);
      io.to(user.socketId).emit("getMessage", message);
    }
  });

  socket.on("disconnect", () => {
    console.log(socket.id)
    users = users.filter((user) => user.socketId !== socket.id);
    console.log("Desconectado: " + users.length)
    io.emit("getOnlineUsers", users);
  });
});

io.listen(3001);
