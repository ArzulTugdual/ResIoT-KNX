 export async function getAllUsers() {

    const response = await fetch('/api/users');
    return await response.json();
  }
  
  export async function createUser(data: any) {
    const response = await fetch(`/api/user`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
      })
    return await response.json();
  } 