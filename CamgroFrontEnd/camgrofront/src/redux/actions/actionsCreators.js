export const UPDATE_TOKEN = 'UPDATE_TOKEN';
export const LOGIN = 'LOGIN';
export const UPDATE_PERSON = 'UPDATE_PERSON';
export const UPDATE_EMAIL = 'UPDATE_EMAIL'

export const updateToken = (token) => (distpach) => {
	distpach({
		type: UPDATE_TOKEN,
		payload: token,
	});
};

export const updateInfoPerson = (person) => (distpach) => {
	distpach({
		type: UPDATE_PERSON,
		payload: person
	})
};

export const login = (login) => (distpach) => {
    distpach({
        type: LOGIN,
        payload: login,
    })
};

export const updateEmail = (email) => (distpach) => {
	distpach({
		type: UPDATE_EMAIL,
		payload: email
	})
}