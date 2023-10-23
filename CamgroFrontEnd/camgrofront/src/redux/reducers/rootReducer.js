import { LOGIN, UPDATE_PERSON, UPDATE_TOKEN, UPDATE_EMAIL } from "../actions/actionsCreators";

const initialState = {
    token : null,
    login : false,
    person: null,
    email: null
}

function rootReducer (state = initialState, action){
    switch(action.type){
        case UPDATE_TOKEN:
            return{
                ...state,
                token : action.payload
            }
        case LOGIN:
            console.log("Entre: " + action.payload)
            return{
                ...state,
                login : action.payload
            }
        case UPDATE_PERSON:
            return{
                ...state,
                person: action.payload
            }
        case UPDATE_EMAIL:
            console.log("UPDATE EMAIL")
            return{
                ...state,
                email: action.payload
            }
        default:
            return state;
    }
}

export default rootReducer

