import { LOGIN, UPDATE_PERSON, UPDATE_TOKEN, UPDATE_EMAIL, UPDATE_ROLE } from "../actions/actionsCreators";

const initialState = {
    token : null,
    login : false,
    person: null,
    email: null,
    role: null
}

function rootReducer (state = initialState, action){
    switch(action.type){
        case UPDATE_TOKEN:
            return{
                ...state,
                token : action.payload
            }
        case LOGIN:
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
            return{
                ...state,
                email: action.payload
            }
        case UPDATE_ROLE:
            return{
                ...state,
                role: action.payload
            }
        default:
            return state;
    }
}

export default rootReducer

