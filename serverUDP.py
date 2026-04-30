import socket

HOST = '0.0.0.0'
PORT = 5001

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((HOST, PORT))

client_addr = None

while True:
    data, addr = sock.recvfrom(1024)
    message = data.decode('utf-8')
    print(message)

    if client_addr is None:
        client_addr = addr

    if message.lower() == "exit":
        break

    response = input()
    sock.sendto(response.encode('utf-8'), client_addr)

    if response.lower() == "exit":
        break

sock.close()